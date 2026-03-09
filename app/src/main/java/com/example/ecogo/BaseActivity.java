package com.example.ecogo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupAutoNavigation(findViewById(android.R.id.content));
    }

    private void setupAutoNavigation(View view) {
        if (view == null) return;

        if (view.isClickable() || view instanceof com.google.android.material.button.MaterialButton || view instanceof com.google.android.material.card.MaterialCardView) {
            view.setOnClickListener(v -> {
                String label = null;
                if (v instanceof TextView) {
                    label = ((TextView) v).getText().toString();
                } else if (v.getContentDescription() != null) {
                    label = v.getContentDescription().toString();
                } else if (v.getId() != View.NO_ID) {
                    String idName = getResources().getResourceEntryName(v.getId());
                    // Heuristic: convert btnSettings or navHome to "Settings" or "Home"
                    label = idName.replace("btn", "").replace("nav", "").replace("ic", "");
                }
                Router.navigate(this, label);
            });
        }

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                setupAutoNavigation(group.getChildAt(i));
            }
        }
    }
}
