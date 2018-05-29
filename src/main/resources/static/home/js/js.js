$(function  () {
    //	点击关闭弹框
    $(".alertcancel_img,#click_cancel").click(function  () {
		$(this).parents(".alert").hide();
		$(".mengban").hide();
	})
    $(".cancel_order,.address_delect,.aftersale_cancel,.quesAnswer_remove,.back_deposit,.cancel_collect,#delect_selectcall").click(function  () {
		$(".mengban,.alertcancel_order").show();
	})
})
