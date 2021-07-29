package zvhir.dev.postboxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Reply extends AppCompatActivity {

    TextView title;
    EditText replybox;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);


        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        title = findViewById(R.id.title);
        title.setText("Reply to letter: " + sessionId );

        replybox = findViewById(R.id.replybox);

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = replybox.getText().toString().trim();

                FirebaseDatabase mDatabaseRef = FirebaseDatabase.getInstance("https://postboxx-f716e-default-rtdb.firebaseio.com/");
                DatabaseReference myRef = mDatabaseRef.getReference("replies");

                myRef.child(sessionId).push().setValue(text);
                Toast.makeText(getApplicationContext(), "Message has been replied!", Toast.LENGTH_LONG).show();



            }
        });



    }
}