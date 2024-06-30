package com.example.lab07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Botones
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnView = view.findViewById(R.id.btnView);

        // Textos
        EditText txtTitle = view.findViewById(R.id.txtTitle);
        EditText txtArtist = view.findViewById(R.id.txtArtist);
        EditText txtTechnique = view.findViewById(R.id.txtTechnique);
        EditText txtCategory = view.findViewById(R.id.txtCategory);
        EditText txtDescription = view.findViewById(R.id.txtDescription);
        EditText txtYear = view.findViewById(R.id.txtYear);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txtTitle.getText().toString();
                String artist = txtArtist.getText().toString();
                String technique = txtTechnique.getText().toString();
                String category = txtCategory.getText().toString();
                String description = txtDescription.getText().toString();
                String year = txtYear.getText().toString();

                if (!title.isEmpty() && !artist.isEmpty() && !technique.isEmpty() && !category.isEmpty() && !description.isEmpty() && !year.isEmpty()) {
                    savePaintingData(title, artist, technique, category, description, year);
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, ViewFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void savePaintingData(String title, String artist, String technique, String category, String description, String year) {
        File file = new File(requireContext().getFilesDir(), "painting_data.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Titulo: " + title + "\nArtista: " + artist + "\nTecnica: " + technique + " Categoría: " + category + "\nDescripción: " + description + "\nAño: " + year);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
}
