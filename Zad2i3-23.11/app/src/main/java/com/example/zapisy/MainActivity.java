package com.example.zapisy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.materialswitch.MaterialSwitch;

import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Spinner spProduct;
    private SeekBar sbQ1, sbQ2, sbQ3, sbQ4, sbQ5;
    private TextView tvQ1, tvQ2, tvQ3, tvQ4, tvQ5, tvSurveySummary;
    private EditText etComment;
    private MaterialSwitch swRecommend;
    private CheckBox cbPublish;
    private Button btnSendSurvey;
    private Button btnClearSurvey;

    private static final String TAG = "ActivityLifecycle";

    private ActivityResultLauncher<Intent> summaryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity - onCreate");
        setContentView(R.layout.activity_survey);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Ankieta");

        summaryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String number = result.getData().getStringExtra(
                                SummaryActivity.EXTRA_RESULT_NUMBER
                        );

                        Toast.makeText(
                                this,
                                getString(R.string.survey_confirmed, number),
                                Toast.LENGTH_LONG
                        ).show();
                    } else {
                        Toast.makeText(
                                this,
                                R.string.survey_cancelled,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        spProduct = findViewById(R.id.spProduct);
        sbQ1 = findViewById(R.id.sbQ1);
        sbQ2 = findViewById(R.id.sbQ2);
        sbQ3 = findViewById(R.id.sbQ3);
        sbQ4 = findViewById(R.id.sbQ4);
        sbQ5 = findViewById(R.id.sbQ5);
        tvQ1 = findViewById(R.id.tvQ1);
        tvQ2 = findViewById(R.id.tvQ2);
        tvQ3 = findViewById(R.id.tvQ3);
        tvQ4 = findViewById(R.id.tvQ4);
        tvQ5 = findViewById(R.id.tvQ5);
        etComment = findViewById(R.id.etComment);
        swRecommend = findViewById(R.id.swRecommend);
        cbPublish = findViewById(R.id.cbPublish);
        btnSendSurvey = findViewById(R.id.btnSendSurvey);
        btnClearSurvey = findViewById(R.id.btnClearSurvey);
        tvSurveySummary = findViewById(R.id.tvSurveySummary);

        setUpProductSpinner();
        setUpListeners();
        setUpClearButton();

        findViewById(R.id.btnAbout).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
    private void setUpClearButton() {
        btnClearSurvey.setOnClickListener(v -> showClearConfirmation());
    }
    private void showClearConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.survey_clear_title)
                .setMessage(R.string.survey_clear_message)
                .setNegativeButton(R.string.survey_clear_cancel, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(R.string.survey_clear_confirm, (dialog, which) -> clearSurvey())
                .show();
    }

    private void setUpProductSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.products,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(adapter);
    }

    private void setUpListeners() {

        cbPublish.setOnCheckedChangeListener((buttonView, isChecked) ->
                btnSendSurvey.setEnabled(isChecked));

        bindSeekBar(sbQ1, tvQ1);
        bindSeekBar(sbQ2, tvQ2);
        bindSeekBar(sbQ3, tvQ3);
        bindSeekBar(sbQ4, tvQ4);
        bindSeekBar(sbQ5, tvQ5);

        btnSendSurvey.setOnClickListener(v -> {
            if (validateSurvey()) {
                openSummary();
            }
        });
    }

    private void openSummary() {
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);

        intent.putExtra(SummaryActivity.EXTRA_PRODUCT,
                spProduct.getSelectedItem().toString());

        intent.putExtra(SummaryActivity.EXTRA_Q1, sbQ1.getProgress());
        intent.putExtra(SummaryActivity.EXTRA_Q2, sbQ2.getProgress());
        intent.putExtra(SummaryActivity.EXTRA_Q3, sbQ3.getProgress());
        intent.putExtra(SummaryActivity.EXTRA_Q4, sbQ4.getProgress());
        intent.putExtra(SummaryActivity.EXTRA_Q5, sbQ5.getProgress());

        intent.putExtra(SummaryActivity.EXTRA_COMMENT,
                etComment.getText().toString().trim());

        intent.putExtra(SummaryActivity.EXTRA_RECOMMEND,
                swRecommend.isChecked());

        intent.putExtra(SummaryActivity.EXTRA_PUBLISH,
                cbPublish.isChecked());

        summaryLauncher.launch(intent);
    }

    private void bindSeekBar(SeekBar seekBar, TextView label) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                label.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) { }

            @Override
            public void onStopTrackingTouch(SeekBar sb) { }
        });
    }

    private String buildSurveySummary() {
        String product = spProduct.getSelectedItem() != null
                ? spProduct.getSelectedItem().toString()
                : "-";
        String recommend = swRecommend.isChecked() ? "tak" : "nie";

        return getString(R.string.survey_summary_format,
                product,
                sbQ1.getProgress(),
                sbQ2.getProgress(),
                sbQ3.getProgress(),
                sbQ4.getProgress(),
                sbQ5.getProgress(),
                etComment.getText().toString().trim(),
                recommend);
    }
    private boolean validateSurvey() {
        if (spProduct.getSelectedItemPosition() == 0) {
            Toast.makeText(this, R.string.error_product, Toast.LENGTH_SHORT).show();
            return false;
        }

        String comment = etComment.getText().toString().trim();
        if (comment.isEmpty()) {
            etComment.setError(getString(R.string.error_comment));
            etComment.requestFocus();
            return false;
        }

        if (!cbPublish.isChecked()) {
            Toast.makeText(this, R.string.error_consent, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private static class SurveyState {
        int productPosition;
        int q1;
        int q2;
        int q3;
        int q4;
        int q5;
        String comment;
        String commentError;
        boolean recommend;
        boolean publish;
        boolean sendEnabled;
        String summary;
    }
    private void clearSurvey() {
        SurveyState previousState = new SurveyState();

        previousState.productPosition = spProduct.getSelectedItemPosition();

        previousState.q1 = sbQ1.getProgress();
        previousState.q2 = sbQ2.getProgress();
        previousState.q3 = sbQ3.getProgress();
        previousState.q4 = sbQ4.getProgress();
        previousState.q5 = sbQ5.getProgress();

        previousState.comment = etComment.getText().toString();
        previousState.commentError = etComment.getError() == null
                ? null
                : etComment.getError().toString();

        previousState.recommend = swRecommend.isChecked();
        previousState.publish = cbPublish.isChecked();
        previousState.sendEnabled = btnSendSurvey.isEnabled();
        previousState.summary = tvSurveySummary.getText().toString();

        spProduct.setSelection(0);

        sbQ1.setProgress(0);
        sbQ2.setProgress(0);
        sbQ3.setProgress(0);
        sbQ4.setProgress(0);
        sbQ5.setProgress(0);

        etComment.setText("");
        etComment.setError(null);

        swRecommend.setChecked(false);
        cbPublish.setChecked(false);

        btnSendSurvey.setEnabled(false);
        tvSurveySummary.setText("");

        Snackbar.make(findViewById(R.id.main), R.string.survey_cleared, Snackbar.LENGTH_LONG)
                .setAction(R.string.survey_undo, v -> restoreSurveyState(previousState))
                .show();
    }
    private void restoreSurveyState(SurveyState state) {
        spProduct.setSelection(state.productPosition);

        sbQ1.setProgress(state.q1);
        sbQ2.setProgress(state.q2);
        sbQ3.setProgress(state.q3);
        sbQ4.setProgress(state.q4);
        sbQ5.setProgress(state.q5);

        etComment.setText(state.comment);
        etComment.setError(state.commentError);

        swRecommend.setChecked(state.recommend);
        cbPublish.setChecked(state.publish);

        btnSendSurvey.setEnabled(state.sendEnabled);
        tvSurveySummary.setText(state.summary);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity - onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        @Override
        public boolean onOptionsItemSelected(android.view.MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.action_about) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            }

            if (id == R.id.action_clear) {
                clearSurvey();
                return true;
            }

            if (id == R.id.action_share) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        tvSurveySummary.getText().toString()
                );

                startActivity(Intent.createChooser(
                        shareIntent,
                        "Udostępnij ankietę"
                ));

                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
}