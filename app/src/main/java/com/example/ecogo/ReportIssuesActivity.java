package com.example.ecogo;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;

public class ReportIssuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report_issues);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView btnSkip = findViewById(R.id.btnSkip);
        AppCompatButton btnNext = findViewById(R.id.btnNext);

        btnSkip.setOnClickListener(v -> navigateToLogin());
        
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(ReportIssuesActivity.this, OnboardingActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void navigateToLogin() {
        Intent intent = new Intent(ReportIssuesActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
