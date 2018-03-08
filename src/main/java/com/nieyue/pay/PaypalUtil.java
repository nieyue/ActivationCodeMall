package com.nieyue.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Paypal支付工具
 * @author 聂跃
 * @date 2018年3月8日
 */
@Configuration
public class PaypalUtil {
	@Value("${myPugin.paypal.client.app}")
    private String clientId;
    @Value("${myPugin.paypal.client.secret}")
    private String clientSecret;
    @Value("${myPugin.paypal.mode}")
    private String mode;
    @Value("${myPugin.paypal.payCancelUrl}")
    private String payCancelUrl;
    @Value("${myPugin.paypal.paySuccessUrl}")
    private String paySuccessUrl;

    public final String PAYPAL_SUCCESS_URL = "/paypal/success";
    public final String PAYPAL_CANCEL_URL = "/paypal/cancel";
    @Bean
    public Map<String, String> paypalSdkConfig(){
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);
        return sdkConfig;
    }
    @Bean
    public OAuthTokenCredential authTokenCredential(){
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }
    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        @SuppressWarnings("deprecation")
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
        apiContext.setConfigurationMap(paypalSdkConfig());
        return apiContext;
    }
    /**
     * paypal支付模式
     * @author 聂跃
     * @date 2018年3月8日
     */
    enum PaypalPaymentIntent {
        //立即进行快速结账付款
        sale,
        //将资金搁置以便以后付款
        authorize,
        //表示买方已同意未来购买。资金被授权并在晚些时候被捕获，而不会搁置资金
        order
    }
    /**
     * paypal支付方式
     * @author 聂跃
     * @date 2018年3月8日
     */
    enum PaypalPaymentMethod {
        //设置信用卡
        credit_card,
        //设置为paypal选择PayPal付款方式
        paypal

    }
    /**
     * 创建Payment
     * @param total
     * @param currency
     * @param method
     * @param intent
     * @param description
     * @param cancelUrl
     * @param successUrl
     * @return
     * @throws PayPalRESTException
     */
    public Payment createPayment(
            Double total,
            String currency,
            PaypalPaymentMethod method,
            PaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext());
    }
    /**
     * 执行回调Payment
     * @param paymentId
     * @param payerId
     * @return
     * @throws PayPalRESTException
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext(), paymentExecute);
    }
   
    /**
     * 支付
     */
    public String pay(
    		Double total,
            String currency,
            String description,
            String cancelUrl,
            String successUrl){
    	String str=null;
    	try {
            Payment payment = createPayment(
            		total,
            		currency,
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    description,
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                	str=links.getHref();
                    return str;
                }
            }
        } catch (PayPalRESTException e) {
        	return str;
        }
        return str;
    }
    /**
     * 获取支付取消url
     */
    public String getPayCancelUrl(){
        return payCancelUrl;
    }
    /**
     * 获取支付成功url
     * paymentId
     * payerId
     */
    public String getPaySuccessUrl(
    		String paymentId,
    		 String payerId
    		){
    	  try {
              Payment payment = executePayment(paymentId, payerId);
              if(payment.getState().equals("approved")){
                 return paySuccessUrl;
              }
          } catch (PayPalRESTException e) {
             return null;
          }
          return null;
    }
}
