package com.example.homework20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.homework20.databinding.FragmentGameBinding;


public class GameFragment extends Fragment {

    FragmentGameBinding binding;
    String firstImage, secondImage, thirdImage, fourImage, answer, prompt, level;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstImage = getArguments().getString("firstImage");
        secondImage = getArguments().getString("secondImage");
        thirdImage = getArguments().getString("thirdImage");
        fourImage = getArguments().getString("fourImage");
        answer = getArguments().getString("answer");
        prompt = getArguments().getString("prompt");
        level = getArguments().getString("level");

        binding.tvLevel.setText(level);

        Glide.with(requireView()).load(firstImage).into(binding.ivFirstImage);
        Glide.with(requireView()).load(secondImage).into(binding.ivSecondImage);
        Glide.with(requireView()).load(thirdImage).into(binding.ivThirdImage);
        Glide.with(requireView()).load(fourImage).into(binding.ivFourImage);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etAnswer.getText().toString().trim().equalsIgnoreCase(answer.toLowerCase())) {
                    Toast.makeText(requireContext(), "Правильно", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(
                            R.id.main_activity, new LevelFragment()).commit();
                } else {
                    Toast.makeText(requireContext(), "Неправильно", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.ivPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
                dialog.setTitle("Подсказка");
                dialog.setMessage(prompt);

                dialog.setPositiveButton("Понятно", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }
}