package zvhir.dev.postboxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Page extends AppCompatActivity {

    Button play;
    Button replybutton;

    EditText songid;
    EditText letterid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        songid = findViewById(R.id.songid);
        letterid = findViewById(R.id.letterid);

        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase mDatabaseRef = FirebaseDatabase.getInstance("https://postboxx-f716e-default-rtdb.firebaseio.com/");
                DatabaseReference myRef = mDatabaseRef.getReference("song");

                String valuesongid = songid.getText().toString().trim();

                Query cr = myRef.orderByChild("code").equalTo(valuesongid);
                cr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String xurl = postSnapshot.child("url").getValue(String.class);

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(xurl));
                            startActivity(browserIntent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(getApplicationContext(), "Wrong code", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

        replybutton = findViewById(R.id.replybutton);
        replybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String xletterid = letterid.getText().toString().trim();

                Intent intent = new Intent(getBaseContext(), Reply.class);
                intent.putExtra("EXTRA_SESSION_ID", xletterid);
                startActivity(intent);
            }
        });
    }
}