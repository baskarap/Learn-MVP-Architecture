package baskara.learnmvp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import baskara.learnmvp.network.VolleyManager;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private EditText inputEmail;
    private EditText inputPassword;
    private Button buttonLogin;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VolleyManager volleyManager = VolleyManager.getInstance(this);
        presenter = new MainPresenter(this, volleyManager);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void renderDefaultView() {
        setContentView(R.layout.activity_main);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void showSuccessAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("OK")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == buttonLogin.getId()) {
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            presenter.doLogin(email, password);
        }
    }
}
