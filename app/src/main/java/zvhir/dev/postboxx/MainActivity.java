package zvhir.dev.postboxx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent (MainActivity.this, welcome.class);
                overridePendingTransition(0, 0);
                x.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(x);
            }
        });

    }
}