package com.example.eval_2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnBotonPulsadoListener{
    private FirstFragment ff;
    private SecondFragment sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ff = new FirstFragment();
        transaction.add(R.id.panelFrame, ff);
        if(grande()){
            sf = new SecondFragment().newInstance("");
            transaction.add(R.id.panelDrc, sf);
        }
        transaction.commit();
    }
    public boolean grande(){
        return findViewById(R.id.panelDrc) != null;
    }

    @Override
    public void OnBotonPulsado(View v) {
        switch (v.getId()) {
            case R.id.b1:
                if (grande()) {
                    EditText et_nombre = findViewById(R.id.et_nombre);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    sf = SecondFragment.newInstance(et_nombre.getText().toString());
                    transaction.replace(R.id.panelDrc, sf);
                    transaction.commit();
                } else {
                    EditText et_nombre = findViewById(R.id.et_nombre);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    sf = SecondFragment.newInstance(et_nombre.getText().toString());
                    transaction.replace(R.id.panelFrame, sf);
                    transaction.commit();
                }
                break;
            default:
                finish();
        }
    }
}
