package com.artmurka.artmurkaapp.Presenter.InterfacesPresenter;


public interface ICheckoutPresenter {
    void getData();
    void postCheckout(String telephone, String message, String email, String pay, String delivery);
    void cityChanged(String msg);
    Boolean isEmailValid(String email);
    boolean isValidPhone(String phone);
    void getAreas();
    void getCities(String cityName);
}
