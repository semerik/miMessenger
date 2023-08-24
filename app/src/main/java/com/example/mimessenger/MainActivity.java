package com.example.mimessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private EditText messageView;
    private TextView chatView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = findViewById(R.id.message);
        chatView = findViewById(R.id.chat_history_main);
        sharedPreferences = getSharedPreferences("ChatHistory", MODE_PRIVATE);

        loadAndDisplayChatHistory();
    }

    private void loadAndDisplayChatHistory() {
        String savedChatHistory = sharedPreferences.getString("history", "");
        chatView.setText(savedChatHistory);
    }

    public void onSendMessage(View view) {
        String messageText = messageView.getText().toString();
        String updatedChatHistory = chatView.getText().toString() + "Dispositivo 1: " + messageText + "\n";

        chatView.setText(updatedChatHistory);
        saveChatHistory(updatedChatHistory);

        Intent intent = new Intent(this, ReciveMessage.class);
        intent.putExtra(ReciveMessage.EXTRA_MESSAGE, messageText);
        startActivity(intent);

    }

    public void clearChatHistory(View view) {
        chatView.setText(""); // Limpia la vista
        saveChatHistory(""); // Guarda el historial vacío
    }

    private void saveChatHistory(String history) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("history", history);
        editor.apply();
    }
}
