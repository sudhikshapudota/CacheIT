package com.example.cacheit;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class details extends AppCompatActivity {

    public EditText card, card2, card3, card4, name;
    public Button nextcard, submit;
    int value = 0;
    DatabaseHelper databaseHelper;
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name = (EditText)findViewById(R.id.name);
        card = (EditText)findViewById(R.id.cc1);
        card2 = (EditText)findViewById(R.id.cc2);
        card3 = (EditText)findViewById(R.id.cc3);
        card4 = (EditText)findViewById(R.id.cc4);
        nextcard = (Button)findViewById(R.id.next);
        submit = (Button)findViewById(R.id.submit);
/*
        user = new User();
*/
        //databaseHelper = new DatabaseHelper(details.this);
        final int namelength = name.length();
        final int cardlength = card.length();
        final int card2length = card2.length();
        final int card3length = card3.length();
        final int card4length = card4.length();
        nextcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value == 0){
                    card2.setVisibility(View.VISIBLE);
                    value = 1;
                }
                else if (value == 1){
                    card3.setVisibility(View.VISIBLE);
                    value = 2;
                }
                else if (value == 2){
                    card4.setVisibility(View.VISIBLE);
                    nextcard.setVisibility(View.GONE);
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            /*if (namelength == 0 || cardlength <= 4 || card2length <= 4 || card3length <=4 || card4length <= 4){

            }*/
            @Override
            public void onClick(View v) {
/*
                saveData();
*/
                Intent i = new Intent(details.this, tabs.class);
                startActivity(i);
            }
        });

    }

/*    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateCard1()) {
            return;
        }

        if (!validateCard2()) {
            return;
        }

        if (!validateCard3()) {
            return;
        }

        if (!validateCard4()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.error_name));
            return false;
        } else {
            name.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCard1() {
        if (name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.error_card));
            return false;
        } else {
            name.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCard2() {
        if (name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.error_card));
            return false;
        } else {
            name.setError(false);
        }

        return true;
    }

    private boolean validateCard3() {
        if (name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.error_card));
            return false;
        } else {
            name.setError(false);
        }

        return true;
    }

    private boolean validateCard4() {
        if (name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.error_card));
            return false;
        } else {
            name.setErrorEnabled(false);
        }

        return true;
    }*/

/*
    @Override
*/
/*
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit:
                Intent i = new Intent(details.this, tabs.class);
                startActivity(i);
                saveData();
                break;
            case R.id.next:
                if (value == 0){
                    card2.setVisibility(View.VISIBLE);
                    value = 1;
                }
                else if (value == 1){
                    card3.setVisibility(View.VISIBLE);
                    value = 2;
                }
                else if (value == 2){
                    card4.setVisibility(View.VISIBLE);
                    nextcard.setVisibility(View.GONE);
                }
                break;
        }
    }
*/

    /*public void saveData(){
            user.setAmount(name.getText().toString().trim());
            user.setDate(card.getText().toString().trim());
            user.setCc2(card2.getText().toString().trim());
            user.setCc3(card3.getText().toString().trim());
            user.setCc4(card4.getText().toString().trim());
            databaseHelper.addUser(user);

            Log.d("Result",user.getId()+user.getName()+user.getCc1()+user.getCc2()+user.getCc3()+user.getCc4());

*//*
        User user = databaseHelper.getReadableDatabase();
*//*

        if (user != null) {
            // adding new note to array list at 0 position
            Log.d("Result",user.getId()+user.getName());
            // Toast.makeText(MainActivitynew.this,n.getName()+n.getAddress()+n.getDesignation(),Toast.LENGTH_LONG).show();
        }

}*/
}
