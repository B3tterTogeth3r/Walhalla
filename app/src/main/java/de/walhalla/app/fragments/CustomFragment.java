package de.walhalla.app.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseUser;

import de.walhalla.app.R;
import de.walhalla.app.firebase.CustomAuthListener;
import de.walhalla.app.utils.Variables;

public abstract class CustomFragment extends Fragment implements CustomAuthListener.sendMain {

    private final String TAG = "CustomFragment";
    public static CustomAuthListener.sendMain authChange;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        authChange = this;

        if (Variables.Firebase.AUTHENTICATION.getCurrentUser() != null) {
            FirebaseUser user = Variables.Firebase.AUTHENTICATION.getCurrentUser();
            if (!user.isEmailVerified()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.error_email_not_verified_title))
                        .setMessage(getString(R.string.error_email_not_verified_message))
                        .setPositiveButton(getString(R.string.yes),
                                (dialog, which) ->
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(task -> {
                                                    if (task.isSuccessful()) {
                                                        Log.d(TAG, "Email send");
                                                    }
                                                    dialog.dismiss();
                                                }))
                        .setNeutralButton(getString(R.string.later), ((dialog, which) -> dialog.dismiss()))
                        .show();
            }
        }
    }

    @Override
    public void onAuthChange() {

    }
}
