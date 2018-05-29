/*********************************************
* JavaScript Timer Control for Internet
* Copyright (C) 2010-2020 FreshFlower
*
* @author FreshFlower <wpt206@163.com>
* @explain http://freshflower.iteye.com/blog/1606222
* @version 2.0
*********************************************/

var JTC = (function () {

    var JTC = function () { },

    config = {
        dayBgColor: ['#90E3F7', '#FFFFFF', '#FFDA44'], //鼠标移动的颜色
        dayColor: ['#0000FF', '#000000', '#888888'], //日期字体颜色:1.上月和下月的日期; 2.本月; 3.不可选时
        format: 'yyyy-MM-dd',   //返回日期值的格式
        outObject: null,
        startDay: null,     
        minDate: 0,         //日期范围最小值(yyyyMMdd) 0.表示不设定
        maxDate: 0,         //日期范围最大值(yyyyMMdd) 0.表示不设定
        ranged: 1,          //是否包含日期边界值: 0.不包含; 1.包含
        showClear: true,    //是否显示清空按钮
        today: null,
        bgDivID: 'JTC_BG_DIV',
        dayPanelId: 'JTC_TheCurDay',
        yearCell: 'JTC_TheCurYear',
        monthCell: 'JTC_TheCurMonth',
        clearButtonId: 'JTC_ClearButton',
        monthSelector: 'JTC_MonthSelector',
        yearSelector: 'JTC_YearSelector'
    },

    lang = {
        monthText: {
            zh: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            en: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec']
        },
        weekDay: { zh: ['日', '一', '二', '三', '四', '五', '六'],
            en: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
        },
        clearBn: { zh: '清空', en: 'Clear' },
        todayBn: { zh: '今天', en: 'Today' },
        closeBn: { zh: '关闭', en: 'Close' },
        yearCell: { zh: '单击选择年份', en: 'Click to select the year' },
        monthCell: { zh: '单击选择月份', en: 'Click to select the month' }
    },

    trim = function (str) {
        return str.replace(/(\s*$)|(^\s*)/g, '');
    },

    $ = function (id, doc) {
        var doc = doc || document;
        return doc.getElementById(id);
    },

    $$ = function (name, doc) {
        var doc = doc || document;
        return doc.createElement(name);
    },

    browser = (function () {
        var ua = navigator.userAgent.toLowerCase();
        return {
            VERSION: parseInt(ua.match(/(msie|firefox|webkit|opera)[\/:\s](\d+)/) ? RegExp.$2 : '0'),
            IE: (ua.indexOf('msie') > -1 && ua.indexOf('opera') == -1),
            GECKO: (ua.indexOf('gecko') > -1 && ua.indexOf('khtml') == -1),
            WEBKIT: (ua.indexOf('applewebkit') > -1),
            OPERA: (ua.indexOf('opera') > -1)
        };
    })(),

    util = {
        today: function () {
            if (config.today != null) { return config.today; }
            return new Date();
        },

        getLangText: function (text) {
            var language = (navigator.appName == 'Netscape') ? navigator.language : navigator.browserLanguage;
            return text[(language.indexOf('en-') >= 0) ? 'en' : 'zh'];
        },

        createTable: function (doc) {
            var table = $$('table', doc);
            table.cellPadding = 0;
            table.cellSpacing = 0;
            table.border = 0;
            table.style.cursor = 'default';
            return table;
        },

        createButton: function (text, style, func, id) {
            var button = $$('input');
            button.setAttribute('type', 'button');
            button.value = text;
            if (id) { button.id = id; }
            util.setObjectStyle(button, style + 'cursor:pointer;border:0px;');
            button.onclick = func;
            return button;
        },

        copyConfig: function () {
            var arg = ['format', 'outObject', 'minDate', 'maxDate', 'ranged', 'showClear', 'startDay'];
            var set = {};
            for (var i = 0; i < arg.length; i++) { set[arg[i]] = config[arg[i]]; }
            config['set'] = set; return config['set'];
        },

        formatDate: function (date, format) {
            var lang = { 'M+': date.getMonth() + 1, 'd+': date.getDate() };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
            }
            for (var key in lang) {
                if (new RegExp('(' + key + ')').test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? lang[key] : ('00' + lang[key]).substr(('' + lang[key]).length));
                }
            }
            return format;
        },

        getCoords: function (ev) {
            ev = ev || window.event;
            return { x: ev.clientX, y: ev.clientY };
        },

        getDocumentElement: function (doc) {
            doc = doc || document;
            return (doc.compatMode != "CSS1Compat") ? doc.body : doc.documentElement;
        },

        getScrollPos: function () {
            var x, y;
            if (browser.IE || browser.OPERA) {
                var el = this.getDocumentElement();
                x = el.scrollLeft; y = el.scrollTop;
            } else {
                x = window.scrollX; y = window.scrollY;
            }
            return { x: x, y: y };
        },

        getElementPos: function (el) {
            var x = 0, y = 0;
            if (el.getBoundingClientRect) {
                var box = el.getBoundingClientRect();
                var el = this.getDocumentElement();
                var pos = this.getScrollPos();
                x = box.left + pos.x - el.clientLeft;
                y = box.top + pos.y - el.clientTop;
            } else {
                x = el.offsetLeft; y = el.offsetTop;
                var parent = el.offsetParent;
                while (parent) {
                    x += parent.offsetLeft; y += parent.offsetTop;
                    parent = parent.offsetParent;
                }
            }
            return { x: x, y: y };
        },

        setObjectStyle: function (obj, style) {
            obj.setAttribute('style', style);
            obj.style.cssText = style;
        },

        setDate: function (dateObj) {
            if (dateObj == null || dateObj == undefined) { return null; }
            var ret = null;
            var x = typeof (dateObj);
            try {
                if (x == 'string') {
                    ret = new Date(trim(dateObj).replace(/[^0-9: ]+/g, '/'));
                } else if (x == 'object') {
                    if (dateObj.getTime()) { ret = new Date(dateObj.getTime()); }
                }
            } catch (e) { ret = null; }
            return ret;
        },

        isLeapYear: function (year) {   //是否为闰年
            return (0 == year % 4 && ((year % 100 != 0) || (year % 400 == 0)))
        },

        getMonthCount: function (yy, mm) {
            var count = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            var days = count[mm - 1];
            if (mm == 2 && util.isLeapYear(yy)) { days++; }
            return days;
        },

        getNYearMonth: function (yy, mm, n) {
            if (n > 0) {
                if (mm + n > 12) { mm = 1; yy++; }
                else { mm++; }
            } else {
                if (mm + n <= 0) { yy--; mm = 12; }
                else { mm--; }
            }
            return { YY: yy, MM: mm };
        },

        getDateNumbers: function (date) {
            return { year: date.getFullYear(), month: date.getMonth() + 1, day: date.getDate() };
        },

        getIntegerOfDay: function (yy, mm, dd) {
            if (yy == null) { return 0; }
            if (arguments.length == 1) {
                var x = util.getDateNumbers(yy);
                return x.year * 10000 + x.month * 100 + x.day;
            } else {
                return yy * 10000 + mm * 100 + dd;
            }
        },

        checkDateRange: function (yyMMdd) {
            var set = config['set'];
            if (set.maxDate == 0 && set.minDate == 0) { return true; }
            if (set.minDate == 0) {
                return set.ranged == 1 ? (yyMMdd <= set.maxDate) : (yyMMdd < set.maxDate);
            } else if (set.maxDate == 0) {
                return set.ranged == 1 ? (yyMMdd >= set.minDate) : (yyMMdd > set.minDate);
            }
            if (set.ranged == 1) {
                return yyMMdd >= set.minDate && yyMMdd <= set.maxDate;
            } else {
                return yyMMdd > set.minDate && yyMMdd < set.maxDate;
            }
        },

        getEventSrcObject: function () {
            var theEvent = window.event;
            if (theEvent == undefined) {
                var caller = arguments.callee.caller;
                while (caller.caller != null) { caller = caller.caller; }
                theEvent = caller.arguments[0];
            }
            return theEvent.srcElement ? theEvent.srcElement : theEvent.target;
        },

        outObjectValue: function (arg) {
            var obj = config['set'].outObject;
            if (arguments.length < 1) {
                return trim(obj.tagName.toLowerCase() == 'input' ? obj.value : obj.innerHTML);
            }
            var str = typeof (arg) == 'object' ? util.formatDate(arg, config['set'].format) : '';
            if (obj.tagName.toLowerCase() == 'input') {
                obj.value = str;
            } else {
                obj.innerHTML = str;
            }
        }
    },

    events = {
        addEvent: function (el, event, listener) {
            if (el.addEventListener) {
                el.addEventListener(event, listener, false);
            } else if (el.attachEvent) {
                el.attachEvent('on' + event, listener);
            }
        },

        removeEvent: function (el, event, listener) {
            if (el.removeEventListener) {
                el.removeEventListener(event, listener, false);
            } else if (el.detachEvent) {
                el.detachEvent('on' + event, listener);
            }
        },

        show: function (top, left) {
            var outv = '';
            var readyDay = util.today();
            if (config['set'].startDay == null) {
                outv = util.outObjectValue();
                if (outv != '') {
                    outv = outv.replace(/[^0-9:]/gi, '/');
                    if ((new Date(outv)).toString().toLowerCase() != 'invalid date') {
                        readyDay = new Date(outv);
                    }
                }
            } else {
                readyDay = config['set'].startDay;
            }

            var div = $(config.bgDivID);
            if (div == null || div == undefined) {
                dialog();
                div = $(config.bgDivID);
            }
            var ymd = util.getDateNumbers(readyDay);
            events.createDay(ymd.year, ymd.month, ymd.day);

            div.style.top = top + 'px';
            div.style.left = left + 'px';
            div.style.display = '';
            events.addEvent(document, 'keydown', events.keyDown);
            events.addEvent(document, 'mousedown', events.mouseDown);
            $(config.clearButtonId).style.display = config['set'].showClear ? '' : 'none';
        },

        setHeaderYM: function (yy, mm) {
            $(config.yearCell).innerHTML = yy;
            $(config.monthCell).innerHTML = util.getLangText(lang.monthText)[mm - 1];
            $(config.monthCell).axis = mm;
        },

        createDay: function (yy, mm, dd) {
            this.dayRangeEv = function (obj, nday, x) {
                var isRange = util.checkDateRange(nday);
                obj.axis = x; obj.className = '';
                obj.style.backgroundColor = config.dayBgColor[1];
                obj.style.color = isRange === true ? config.dayColor[x === 0 ? 1 : 0] : config.dayColor[2];
                obj.style.cursor = isRange === true ? 'pointer' : 'default';
                obj.onmousemove = isRange === true ? events.dayOnMouseMove : events.emptyFunc;
                obj.onmouseout = isRange === true ? events.dayOnMouseOut : events.emptyFunc;
                obj.onclick = isRange === true ? events.dayOnClick : events.emptyFunc;
            };

            var dd = dd || 0;
            events.setHeaderYM(yy, mm);
            var date = new Date(yy, mm - 1, 1);
            var firstCount = date.getDay();
            firstCount = firstCount < 2 ? firstCount + 7 : firstCount;

            //上个月的尾数
            var pre = util.getNYearMonth(yy, mm, -1);
            var preMonthDay = util.getMonthCount(pre.YY, pre.MM);
            var obj = null;
            for (var i = firstCount - 1; i >= 0; i--) {
                obj = $(config.dayPanelId + i);
                obj.innerHTML = preMonthDay - firstCount + i + 1;
                this.dayRangeEv(obj, util.getIntegerOfDay(pre.YY, pre.MM, preMonthDay - firstCount + i + 1), -1);
            }
            //本月
            var monthDays = util.getMonthCount(yy, mm);
            for (var i = 0; i < monthDays; i++) {
                obj = $(config.dayPanelId + (firstCount + i));
                obj.innerHTML = i + 1;
                this.dayRangeEv(obj, util.getIntegerOfDay(yy, mm, i + 1), 0);
                if ((dd != i + 1) || obj.style.cursor == 'default') {
                    obj.style.backgroundColor = config.dayBgColor[1];
                    obj.className = '';
                } else {
                    obj.style.backgroundColor = config.dayBgColor[0];
                    obj.className = 'JTC_CHOOSEDAY';
                }
            }
            //下个月
            var xx = firstCount + monthDays;
            var next = util.getNYearMonth(yy, mm, 1);
            for (var i = 0; i < 42 - firstCount - monthDays; i++) {
                obj = $(config.dayPanelId + (xx + i));
                obj.innerHTML = i + 1;
                this.dayRangeEv(obj, util.getIntegerOfDay(next.YY, next.MM, i + 1), 1);
            }
        },

        dayOnMouseMove: function () {
            this.style.backgroundColor = config.dayBgColor[0];
        },

        dayOnMouseOut: function () {
            if (this.className != 'JTC_CHOOSEDAY') {
                this.style.backgroundColor = config.dayBgColor[1];
            }
        },

        dayOnClick: function () {
            var year = parseInt($(config.yearCell).innerHTML.match(/\d+/g)),
            month = parseInt($(config.monthCell).axis),
            day = parseInt(this.innerHTML);

            if (this.axis != '0') {
                var n = day > 20 ? -1 : 1;
                var ready = util.getNYearMonth(year, month, n);
                year = ready.YY; month = ready.MM;
            }

            var date = new Date(year + '/' + month + '/' + day);
            util.outObjectValue(date);
            events.hideLayout();
        },

        emptyFunc: function () { },

        turnMonth: function (n) {
            var year = parseInt($(config.yearCell).innerHTML.match(/\d+/g)),
            month = parseInt($(config.monthCell).axis);
            if (n != 0) {
                var xday = util.getNYearMonth(year, month, n);
                year = xday.YY; month = xday.MM;
            }
            events.createDay(year, month);
        },

        showSelector: function (flag) {
            var flag = flag || 1;
            events.hideSelector(flag == 1 ? 2 : 1);
            var div = flag == 1 ? $(config.yearSelector) : $(config.monthSelector);
            var bool = (div == undefined || div == null);
            if (bool) {
                div = $$('div');
            } else {
                if (div.style.display != 'none') { events.hideSelector(flag); return; }
                var divChildren = div.childNodes;
                for (var i = divChildren.length - 1; i >= 0; i--) {
                    div.removeChild(divChildren[i]);
                }
            }
            var coord = {
                x: parseInt($(config.bgDivID).style.left.match(/\d+/g)) + (flag == 1 ? 32 : 85),
                y: parseInt($(config.bgDivID).style.top.match(/\d+/g)) + 25
            };
            div.id = flag == 1 ? config.yearSelector : config.monthSelector;
            var _style = 'position:absolute; z-index:20010; background-color:#FFFFFF; width:78px; height:120px; top:' +
                coord.y + 'px; left:' + coord.x + 'px; border:1px solid #A77AEB; padding:2px 2px;';
            util.setObjectStyle(div, _style);
            var table = util.createTable();
            if (flag == 1) {
                var start = parseInt($(config.yearCell).innerHTML.match(/\d+/g)) - 5;
                events.yearSelector(table, start);
            } else {
                events.monthSelector(table);
            }
            div.appendChild(table);
            if (bool) {
                document.body.appendChild(div);
            } else {
                div.style.display = '';
            }
        },

        yearSelector: function (table, start, rep) {
            var objid = ['JTC_YEATSELECTOR_PRET', 'JTC_YEATSELECTOR_NEXT', 'JTC_YEARSELECTOR'];
            var rep = rep || 0;
            if (rep == 1) {
                for (var i = 0; i < 10; i++) {
                    $(objid[2] + i).innerHTML = start + i;
                }
                $(objid[0]).onclick = function () { events.yearSelector(table, start - 10, 1); };
                $(objid[1]).onclick = function () { events.yearSelector(table, start + 10, 1); };
                return;
            }

            var row, cell;
            for (var i = 0; i < 5; i++) {
                row = table.insertRow(i);
                for (var j = 0; j < 2; j++) {
                    cell = row.insertCell(j);
                    cell.id = objid[2] + (j == 0 ? i : i + 5);
                    cell.innerHTML = start + i + j * 5;
                    var cellStyle = 'width:37px;;height:20px; text-align:center;font-size:12px;cursor:pointer;';
                    util.setObjectStyle(cell, cellStyle);
                    cell.onmousemove = events.dayOnMouseMove;
                    cell.onmouseout = events.dayOnMouseOut;
                    cell.onclick = function () {
                        $(config.yearCell).innerHTML = this.innerHTML;
                        events.turnMonth(0);
                        events.hideSelector(1);
                    };
                }
            }

            row = table.insertRow(5);
            cell = row.insertCell(0);
            cell.colSpan = 2;
            var bnStyle = 'border:0px;background-color:#FFFFFF;width:24px;height:20px; margin:0px 0px;';
            var button = util.createButton('←', bnStyle, function () {
                var s1 = start - 10; events.yearSelector(table, s1, 1);
            }, objid[0]);
            cell.appendChild(button);
            button = util.createButton('×', bnStyle, function () { events.hideSelector(1); });
            cell.appendChild(button);
            button = util.createButton('→', bnStyle, function () {
                var s2 = start + 10; events.yearSelector(table, s2, 1);
            }, objid[1]);
            cell.appendChild(button);
        },

        monthSelector: function (table) {
            var array = util.getLangText(lang.monthText);
            for (var i = 0; i < 6; i++) {
                var row = table.insertRow(i);
                for (var j = 0; j < 2; j++) {
                    var cell = row.insertCell(j);
                    cell.innerHTML = array[i + j * 6];
                    cell.axis = i + 1 + j * 6;
                    var cellStyle = 'width:37px;;height:20px; text-align:center;font-size:12px;cursor:pointer;';
                    util.setObjectStyle(cell, cellStyle);
                    cell.onmousemove = events.dayOnMouseMove;
                    cell.onmouseout = events.dayOnMouseOut;
                    cell.onclick = function () {
                        $(config.monthCell).axis = this.axis;
                        events.turnMonth(0);
                        events.hideSelector(2);
                    };
                }
            }
        },

        hideSelector: function (flag) {
            if (flag == 2) {
                if ($(config.monthSelector)) { $(config.monthSelector).style.display = 'none'; }
            } else {
                if ($(config.yearSelector)) { $(config.yearSelector).style.display = 'none'; }
            }
        },

        hideLayout: function () {
            var div = $(config.bgDivID);
            events.hideSelector(1);
            events.hideSelector(2);
            div.style.display = 'none';
            events.removeEvent(document, 'keydown', events.keyDown);
            events.removeEvent(document, 'mousedown', events.mouseDown);
        },

        keyDown: function (ev) {
            ev = ev || window.event;
            if (ev.keyCode == 27) { events.hideLayout(); }
        },

        mouseDown: function (ev) {
            var div = $(config.bgDivID);
            if (div.style.display == 'none') { return; }

            var ymFlag = 0;
            if ($(config.yearSelectCtrl)) { ymFlag = 1; }
            if ($(config.monthSelectCtrl)) { ymFlag = 1; }

            var minLeft, minTop, maxLeft, maxTop;
            var pos = util.getElementPos(div);
            minLeft = pos.x; minTop = pos.y;
            maxLeft = minLeft + 170;
            maxTop = minTop + 190;

            var scrol = util.getScrollPos();
            var mouse = util.getCoords(ev)
            var x = scrol.x + mouse.x;
            var y = scrol.y + mouse.y;
            if (ymFlag == 1) {
                if (x < minLeft || x > maxLeft) { events.hideLayout(); }
                return;
            }
            if (x < minLeft || x > maxLeft || y < minTop || y > maxTop) {
                events.hideLayout();
            }
        }
    },

    dialog = function () {
        this.getHeaderPanel = function () {
            var table = util.createTable();
            var row = table.insertRow(0);
            var cell = row.insertCell(0);
            cell.style.width = '30px';
            cell.style.height = '23px';
            var bnStyle = 'width:28px; height:21px; cursor: pointer; border: 0px none; background-color:#FFFFFF;';
            var bn = util.createButton('<', bnStyle, function () { events.turnMonth(-1); }, 'bn_preMonth');
            cell.appendChild(bn);

            cell = row.insertCell(1);
            var style = 'cursor:pointer;width:54px; line-height:20px;background-color:#FFFFFF;';
            util.setObjectStyle(cell, style);
            cell.id = config.yearCell;
            cell.title = util.getLangText(lang.yearCell);
            cell.onmousemove = function () { this.style.backgroundColor = config.dayBgColor[2]; };
            cell.onmouseout = function () { this.style.backgroundColor = config.dayBgColor[1]; };
            cell.onclick = function () { events.showSelector(1); };

            cell = row.insertCell(2);
            util.setObjectStyle(cell, style);
            cell.id = config.monthCell;
            cell.title = util.getLangText(lang.monthCell);
            cell.onmousemove = function () { this.style.backgroundColor = config.dayBgColor[2]; };
            cell.onmouseout = function () { this.style.backgroundColor = config.dayBgColor[1]; };
            cell.onclick = function () { events.showSelector(2); };

            cell = row.insertCell(3);
            cell.style.width = '30px';
            bn = util.createButton('>', bnStyle, function () { events.turnMonth(1); });
            cell.appendChild(bn);

            return table;
        };

        this.getWeekDayPanel = function () {
            var table = util.createTable();
            style = 'width:23px; height:20px; cursor:pointer; border-right:1px solid #EEEEEE; border-bottom:1px solid #EEEEEE; line-height:16px;';
            var panelId = 0; var row = null;
            for (var m = 0; m < 6; m++) {
                row = table.insertRow(m);
                for (var n = 0; n < 7; n++) {
                    cell = row.insertCell(n);
                    cell.id = config.dayPanelId + panelId;
                    util.setObjectStyle(cell, style);
                    panelId++;
                }
            }

            row = table.insertRow(0);
            var week = util.getLangText(lang.weekDay);
            var style = 'height:23px; border-top:0px; border-bottom:1px solid #008FEE; border-right:0px; border-left:0px;background-color:#FFFFFF;';
            for (var i = 0; i < 7; i++) {
                var cell = row.insertCell(i);
                util.setObjectStyle(cell, style);
                cell.innerHTML = week[i];
            }
            return table;
        };

        this.getBottomPanel = function () {
            var table = util.createTable();
            var row = table.insertRow(0);
            var cell = row.insertCell(0);
            cell.style.width = '48px';
            var bnStyle = 'font-size:12px;width:38px; border:0px; background-color:#FFFFFF;';

            cell = row.insertCell(1);
            cell.style.width = '38px';
            var bn = util.createButton(util.getLangText(lang.clearBn), bnStyle, function () { util.outObjectValue(''); }, config.clearButtonId);
            cell.appendChild(bn);

            cell = row.insertCell(2);
            cell.style.width = '38px';
            bn = util.createButton(util.getLangText(lang.todayBn), bnStyle, function () {
                var xnow = util.getDateNumbers(util.today());
                events.createDay(xnow.year, xnow.month, xnow.day);
            });
            cell.appendChild(bn);

            cell = row.insertCell(3);
            cell.style.width = '38px';
            bn = util.createButton(util.getLangText(lang.closeBn), bnStyle, events.hideLayout);
            cell.appendChild(bn);

            return table;
        };

        var container = util.createTable();
        var style = 'border:1px solid #008FEE; font-size:12px; text-align:center;';
        util.setObjectStyle(container, style);

        var row = container.insertRow(0);
        var cell = row.insertCell(0);
        cell.appendChild(this.getHeaderPanel());

        row = container.insertRow(1);
        cell = row.insertCell(0);
        cell.appendChild(this.getWeekDayPanel());

        row = container.insertRow(2);
        cell = row.insertCell(0);
        cell.appendChild(this.getBottomPanel());

        var div = $$('div');
        div.id = config.bgDivID;
        _style = 'position:absolute; width:171px; height:190px;z-index:20000; font-size:12px;display:none;background-color:#FFFFFF;';
        util.setObjectStyle(div, _style);

        if (browser.IE && browser.VERSION < 7) {
            var iframe = $$('iframe');
            _style = 'position:absolute; width:170px; height:185px;z-index:-1;';
            util.setObjectStyle(iframe, _style);
            iframe.frameBorder = 0;
            iframe.scrolling = 'no';
            iframe.src = 'about:blank';
            div.appendChild(iframe);
        }
        div.appendChild(container);
        document.body.appendChild(div);
    };

    JTC.setday = function (args) {
        var obj = util.getEventSrcObject();
        var x = util.copyConfig();
        x.outObject = obj;
        this.checkObj = function (str) { return $(str) != null && $(str) != undefined; };
        if (args) {
            if (typeof (args) == 'string' && this.checkObj(args)) {
                x.outObject = $(args);
            }
            else if (typeof (args.outObject) == 'string' && this.checkObj(args.outObject)) {
                x.outObject = $(args.outObject);
            } else if (typeof (args.outObject) == 'object') { x.outObject = args.outObject; }

            x.showClear = (args.showClear || config.showClear) === false ? false : true;
            x.format = args.format || config.format;
            if (args.today) { JTC.setToday(args.today); }
            if (args.minDate != undefined || args.maxDate != undefined) {
                x.minDate = util.getIntegerOfDay(util.setDate(args.minDate));
                x.maxDate = util.getIntegerOfDay(util.setDate(args.maxDate));
                x.ranged = (args.ranged || config.ranged) === false ? 0 : 1;
            }
            if (args.readOnly === true) { x.outObject.readOnly = true; }
            if (args.startDay) { x.startDay = util.setDate(args.startDay); }
        }

        var pos = { top: obj.offsetTop, left: obj.offsetLeft, height: obj.clientHeight };
        while (obj = obj.offsetParent) {
            pos.top += obj.offsetTop;
            pos.left += obj.offsetLeft;
        }
        var objTop = (typeof (obj) == 'image') ? pos.top + pos.height : pos.top + pos.height + 5;
        events.show(objTop, pos.left);
    };

    JTC.setToday = function (dateObj) {
        config.today = util.setDate(dateObj);
    };

    JTC.setDateRange = function (minDate, maxDate, ranged) {
        config.minDate = util.getIntegerOfDay(util.setDate(minDate));
        config.maxDate = util.getIntegerOfDay(util.setDate(maxDate));
        config.ranged = ranged === false ? 0 : 1;
    };

    JTC.setDateFormat = function (format) {
        config.format = format || config.format;
    };

    JTC.setStartDay = function (date) {
        config.startDay = util.setDate(date);
    }
    return JTC;
})();
