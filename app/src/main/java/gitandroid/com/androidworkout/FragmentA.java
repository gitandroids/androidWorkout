package gitandroid.com.androidworkout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private FragmentAListener listener;
    private EditText editText;
    private Button buttonOK;

    // In order to communicate with the underlying activity we have to create an Interface..
    // Interface method. CharSequence is more general than a String. dont need to cast
    // So similar to onClickListener interface.We have to override onClick method.
    // Here we will implement FragmentAListener and override onInputASent method.
    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        editText = v.findViewById(R.id.edit_text);
        buttonOK = v.findViewById(R.id.button_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInputASent(input);
                // This will be sent to Whoever implements this Interface..
                //Asign this listener on attach to Activity.
            }
        });
        return v;
    }

    public void updateEditText(CharSequence newText) {
        editText.setText(newText);
    }

    //So important Code Sinippet and Pattern for Fragments.
    //onAttach will be called when our fragment Attached to Activity.
    //First we need to check if our Activity implements the interface.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) { // The context is our Activity.. Here we check if our Activity implements the interface.
            listener = (FragmentAListener) context; // We assign listener variable to the Activity with casting.
        } else {
            throw new RuntimeException(context.toString()  //This will say that MainActivity must implement the interface.
                    + " must implement FragmentAListener");
        }
    }

    //Called when we remove the fragment from the Activity.
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null; // We dont need this referance to our Activity anymore
    }
}
