package com.example.lab07;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ViewFragment extends Fragment {

    private static final int CREATE_FILE_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView displayTextView = view.findViewById(R.id.display_text_view);
        displayPaintingData(displayTextView);

        Button downloadButton = view.findViewById(R.id.download_button);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile();
            }
        });
    }

    private void displayPaintingData(TextView displayTextView) {
        File file = new File(requireContext().getFilesDir(), "painting_data.txt");
        if (file.exists()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                String data = new String(buffer);
                displayTextView.setText(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            displayTextView.setText("Datos no disponibles");
        }
    }

    private void downloadFile() {
        File file = new File(requireContext().getFilesDir(), "painting_data.txt");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, "painting_data.txt");
            startActivityForResult(intent, CREATE_FILE_REQUEST_CODE);
        } else {
            Log.d("FileError", "Archivo no existe");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        File file = new File(requireContext().getFilesDir(), "painting_data.txt");
                        String content = readFileContent(file);
                        OutputStream outputStream = requireContext().getContentResolver().openOutputStream(uri);
                        if (outputStream != null) {
                            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                            writer.write(content);
                            writer.close();
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String readFileContent(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            InputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                contentBuilder.append(new String(buffer, 0, bytesRead));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static ViewFragment newInstance() {
        return new ViewFragment();
    }
}
