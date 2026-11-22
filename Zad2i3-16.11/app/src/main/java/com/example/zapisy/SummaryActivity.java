package com.example.zapisy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    // Klucze do odbierania danych z MainActivity
    public static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    public static final String EXTRA_Q1 = "EXTRA_Q1";
    public static final String EXTRA_Q2 = "EXTRA_Q2";
    public static final String EXTRA_Q3 = "EXTRA_Q3";
    public static final String EXTRA_Q4 = "EXTRA_Q4";
    public static final String EXTRA_Q5 = "EXTRA_Q5";
    public static final String EXTRA_COMMENT = "EXTRA_COMMENT";
    public static final String EXTRA_RECOMMEND = "EXTRA_RECOMMEND";
    public static final String EXTRA_PUBLISH = "EXTRA_PUBLISH";
    public static final String EXTRA_RESULT_NUMBER = "EXTRA_RESULT_NUMBER";
    private static final String TAG = "ActivityLifecycle";

    private TextView tvSummary;
    private Button btnConfirm, btnEdit, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "SummaryActivity - onCreate");
        setContentView(R.layout.activity_summary);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Podsumowanie");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(v -> finish());

        tvSummary = findViewById(R.id.tvSummary);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnEdit = findViewById(R.id.btnEdit);
        btnShare = findViewById(R.id.btnShare);

        displaySummary();

        btnConfirm.setOnClickListener(v -> confirmSurvey());

        btnEdit.setOnClickListener(v -> finish());

        btnShare.setOnClickListener(v -> shareSummary());
    }

    private void displaySummary() {
        Intent intent = getIntent();

        String product = intent.getStringExtra(EXTRA_PRODUCT);
        if (product == null) {
            product = "-";
        }


        int q1 = intent.getIntExtra(EXTRA_Q1, 0);
        int q2 = intent.getIntExtra(EXTRA_Q2, 0);
        int q3 = intent.getIntExtra(EXTRA_Q3, 0);
        int q4 = intent.getIntExtra(EXTRA_Q4, 0);
        int q5 = intent.getIntExtra(EXTRA_Q5, 0);

        String comment = intent.getStringExtra(EXTRA_COMMENT);
        if (comment == null) {
            comment = "";
        }

        boolean recommend = intent.getBooleanExtra(EXTRA_RECOMMEND, false);
        boolean publish = intent.getBooleanExtra(EXTRA_PUBLISH, false);

        String recommendText = recommend
                ? getString(R.string.yes)
                : getString(R.string.no);

        String publishText = publish
                ? getString(R.string.yes)
                : getString(R.string.no);

        String summary = getString(
                R.string.summary_format,
                product, q1, q2, q3, q4, q5,
                comment, recommendText, publishText
        );

        tvSummary.setText(summary);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Opinia: " + product);
    }

    private void confirmSurvey() {
        String resultNumber = "ANK-" + (System.currentTimeMillis() % 1000000);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_RESULT_NUMBER, resultNumber);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void shareSummary() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                tvSummary.getText().toString()
        );

        try {
            startActivity(Intent.createChooser(
                    shareIntent,
                    getString(R.string.share_via)
            ));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(
                    this,
                    R.string.no_share_app,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SummaryActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SummaryActivity - onDestroy");
    }


}