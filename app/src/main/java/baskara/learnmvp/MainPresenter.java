package baskara.learnmvp;

import org.json.JSONObject;

import baskara.learnmvp.network.GetWeatherVolleyRequest;
import baskara.learnmvp.network.VolleyListener;
import baskara.learnmvp.network.VolleyManager;
import baskara.learnmvp.network.VolleyRequest;

public class MainPresenter {
    private final MainView view;
    private final VolleyManager volleyManager;

    public MainPresenter(MainView view, VolleyManager volleyManager) {
        this.view = view;
        this.volleyManager = volleyManager;
        view.renderDefaultView();
    }

    public void doLogin(String email, String password) {
        if (validateString(email) && validateString(password)) {
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "APPID";
            String location = "surabaya";
            GetWeatherVolleyRequest request = new GetWeatherVolleyRequest();
            request.putParams(QUERY_PARAM, location);
            request.putParams(FORMAT_PARAM, "json");
            request.putParams(UNITS_PARAM, "metric");
            request.putParams(DAYS_PARAM, 7);
            request.putParams(APPID_PARAM, "fd46595ae6d61340ff374a8c836cb256");
            request.setListener(new VolleyListener() {
                @Override
                public void onSuccess(VolleyRequest volleyRequest, JSONObject response) {
                    view.showSuccessAlert("LOGIN SUCCESS");
                }

                @Override
                public void onError(VolleyRequest volleyRequest, String message) {
                    view.showSuccessAlert("LOGIN FAILED");
                }
            });
            volleyManager.createRequest(request, "LOGIN");
        } else {
            view.showErrorAlert("Email and Password should not be empty");
        }
    }

    private boolean validateString(String email) {
        return email != null && email.length() != 0;
    }
}
