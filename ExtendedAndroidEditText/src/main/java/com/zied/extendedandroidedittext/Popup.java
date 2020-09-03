package com.zied.extendedandroidedittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


public class Popup {

    private PopupWindow popupWindow;
    private PopupWindow popupWindowContent;
    private ExtendedView extendedView;

    public Popup(Context context, final ExtendedView extendedView) {

        this.extendedView = extendedView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.help_layout, null, false);
        View viewContent = inflater.inflate(R.layout.help_content_layout, null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(7, 7, 7, 7);
        viewContent.setLayoutParams(params);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindowContent = new PopupWindow(viewContent, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        extendedView.getAnchorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        extendedView.getMainView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b && extendedView.getAnchorView().isShown()) showPanel();
                else removePanel();
            }
        });
        ((EditText) extendedView.getMainView()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                extendedView.validate();
            }
        });
    }

    public void toggle() {
        if (popupWindow.isShowing()) {
            removePanel();
        } else showPanel();
    }


    public void showPanel() {
        popupWindow.showAsDropDown(extendedView.getAnchorView());
        popupWindowContent.showAsDropDown(popupWindow.getContentView());
    }

    public void removePanel() {
        popupWindow.dismiss();
        popupWindowContent.dismiss();
    }

}
