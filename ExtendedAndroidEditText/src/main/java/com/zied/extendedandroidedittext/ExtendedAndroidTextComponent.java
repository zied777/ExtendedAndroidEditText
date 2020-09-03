package com.zied.extendedandroidedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zied.ziedvalidator.ZValidator;

public class ExtendedAndroidTextComponent extends LinearLayout implements ExtendedView {

    private boolean isValid = true;
    private EditText editText;
    private Button button;
    private Popup popup;
    private ZValidator zValidator;

    public ExtendedAndroidTextComponent(final Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.extended_et_layout, this, true);

        button = findViewById(R.id.etcl_button);
        editText = findViewById(R.id.etcl_editText);
        popup = new Popup(getContext(), this);
    }

    public String getValue() {
        return editText.getText().toString();
    }

    public boolean isValid() {
        return isValid;
    }

    public void validate() {
        popup.removePanel();
        isValid = zValidator != null ? zValidator.validate(getValue()) : true;
        button.setVisibility(isValid ? GONE : VISIBLE);
    }

    @Override
    public View getAnchorView() {
        return button;
    }

    @Override
    public View getMainView() {
        return editText;
    }

    public void setzValidator(ZValidator zValidator) {
        this.zValidator = zValidator;
    }
}
