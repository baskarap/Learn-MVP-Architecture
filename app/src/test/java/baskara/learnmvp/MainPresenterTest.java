package baskara.learnmvp;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import baskara.learnmvp.network.GetWeatherVolleyRequest;
import baskara.learnmvp.network.VolleyListener;
import baskara.learnmvp.network.VolleyManager;
import baskara.learnmvp.network.VolleyRequest;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView view;
    @Mock
    VolleyManager volleyManager;
    @Mock
    GetWeatherVolleyRequest mockRequest;
    @Mock
    JSONObject mockJson;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter(view, volleyManager);
    }

    @Test
    public void testShouldShowErrorWhenEmailIsEmpty() throws Exception {
        presenter.doLogin(null, "password");
        verify(view).showErrorAlert("Email and Password should not be empty");
    }

    @Test
    public void testShouldShowErrorWhenEmailLengthIsZero() throws Exception {
        presenter.doLogin("", "password");
        verify(view).showErrorAlert("Email and Password should not be empty");
    }

    @Test
    public void testShouldShowSuccessLogin() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                VolleyRequest request = (VolleyRequest) invocation.getArguments()[0];
                VolleyListener listener = request.getListener();
                listener.onSuccess(mockRequest, mockJson);
                return null;
            }
        }).when(volleyManager).createRequest(Matchers.<VolleyRequest>any(), Matchers.anyString());
        presenter.doLogin("email", "password");
        verify(view).showSuccessAlert("LOGIN SUCCESS");
    }
}