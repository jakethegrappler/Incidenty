//package cz.cvut.fel.incidenty.rest.handler;
//
///**
// * Třída obsahující informace o chybě, které mohou být odeslány klientovi ve formátu JSON, aby byl informován o tom, co se pokazilo.
// */
//public class ErrorInfo {
//
//    private String message;
//    private String requestUri;
//
//    /**
//     * Defaultní konstruktor.
//     */
//    public ErrorInfo() {
//    }
//
//    /**
//     * Vytváří instanci {@link ErrorInfo} s poskytnutou zprávou a URI požadavku.
//     *
//     * @param message Zpráva o chybě
//     * @param requestUri URI požadavku, který vedl k chybě
//     */
//    public ErrorInfo(String message, String requestUri) {
//        this.message = message;
//        this.requestUri = requestUri;
//    }
//
//    /**
//     * Vrací zprávu o chybě.
//     *
//     * @return Zpráva o chybě
//     */
//    public String getMessage() {
//        return message;
//    }
//
//    /**
//     * Nastavuje zprávu o chybě.
//     *
//     * @param message Zpráva o chybě
//     */
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    /**
//     * Vrací URI požadavku, který vedl k chybě.
//     *
//     * @return URI požadavku
//     */
//    public String getRequestUri() {
//        return requestUri;
//    }
//
//    /**
//     * Nastavuje URI požadavku, který vedl k chybě.
//     *
//     * @param requestUri URI požadavku
//     */
//    public void setRequestUri(String requestUri) {
//        this.requestUri = requestUri;
//    }
//
//    /**
//     * Vrací textovou reprezentaci {@link ErrorInfo}.
//     *
//     * @return Textová reprezentace {@link ErrorInfo}
//     */
//    @Override
//    public String toString() {
//        return "ErrorInfo{" + requestUri + ", message = " + message + "}";
//    }
//}
