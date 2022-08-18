package com.mizoo.askar.calculator.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mizoo.askar.calculator.R;
import com.mizoo.askar.calculator.databinding.ActivityMainBinding;
import com.mizoo.askar.calculator.viewModel.CalViewModel;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CalViewModel calViewModel;
    TextView inputTextView, outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        outputTextView = findViewById(R.id.output);
        inputTextView = findViewById(R.id.tvMyInput);
        inputTextView.setText("hello");


    }

}