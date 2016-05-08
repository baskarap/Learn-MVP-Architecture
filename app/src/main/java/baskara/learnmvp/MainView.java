package baskara.learnmvp;

public interface MainView {

    void renderDefaultView();

    void showToast(String message);

    void showErrorAlert(String message);

    void showSuccessAlert(String message);
}
