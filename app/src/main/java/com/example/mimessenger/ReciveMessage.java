package com.example.mimessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class ReciveMessage extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";
    private EditText messageView;
    private TextView chatView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_message);

        messageView = findViewById(R.id.message);
        chatView = findViewById(R.id.chat_history_receive);
        sharedPreferences = getSharedPreferences("ChatHistory", MODE_PRIVATE);

        loadAndDisplayChatHistory();
    }

    private void loadAndDisplayChatHistory() {
        String savedChatHistory = sharedPreferences.getString("history", "");
        chatView.setText(savedChatHistory);
    }

    public void onSendMessage(View view) {
        String messageText = messageView.getText().toString();
        String updatedChatHistory = chatView.getText().toString() + "Dispositivo 2: " + messageText + "\n";

        chatView.setText(updatedChatHistory);
        saveChatHistory(updatedChatHistory);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, messageText);
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
