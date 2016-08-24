package fhcode.firebasepushnotificationstutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    int recievedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle i = getIntent().getExtras();
        if (i != null) {
            //Retriving
            recievedID = i.getInt("ID");
            Log.d("ID value from push not", String.valueOf(recievedID));
        }
        //Example of usage in mainactivity
        if (recievedID == 1) {
            //do something
        } else {
            //do something
        }


    }
}
