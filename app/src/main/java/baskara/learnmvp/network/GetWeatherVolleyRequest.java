package baskara.learnmvp.network;

public class GetWeatherVolleyRequest extends GetVolleyRequest {
    public GetWeatherVolleyRequest() {
        super("http://api.openweathermap.org/data/2.5/forecast/daily");
    }
}
