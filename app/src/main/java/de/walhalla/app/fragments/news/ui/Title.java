package de.walhalla.app.fragments.news.ui;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import de.walhalla.app.R;
import de.walhalla.app.fragments.news.Fragment;
import de.walhalla.app.utils.Variables;

public class Title extends Fragment {
    public Title() {
    }

    @NotNull
    public static TableLayout load() {
        TableLayout titleTL = new TableLayout(f.getContext());
        TableRow titleRow = new TableRow(f.getContext());
        titleTL.addView(titleRow);
        RelativeLayout topRow = new RelativeLayout(f.getContext());
        titleRow.addView(topRow, new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, //Width
                TableRow.LayoutParams.MATCH_PARENT, //Height
                1.0f
        ));

        title = new TextView(f.getContext());
        abort = new ImageButton(f.getContext());
        done = new ImageButton(f.getContext());
        add = new ImageButton(f.getContext());

        title.setId(R.id.messages_title);
        abort.setId(R.id.close);
        done.setId(R.id.send);
        add.setId(R.id.add);

        topRow.addView(abort);
        topRow.addView(title);
        topRow.addView(done);
        topRow.addView(add);

        title.setTextSize((int) (10 * scale + 0.75f));
        abort.setImageResource(R.drawable.ic_close);
        done.setImageResource(R.drawable.ic_done);
        add.setImageResource(R.drawable.ic_add);

        abort.setBackgroundColor(f.getResources().getColor(R.color.background, null));
        done.setBackgroundColor(f.getResources().getColor(R.color.background, null));
        add.setBackgroundColor(f.getResources().getColor(R.color.background, null));

        RelativeLayout.LayoutParams abortParams = (RelativeLayout.LayoutParams) abort.getLayoutParams();
        abortParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //abortParams.addRule(RelativeLayout.LEFT_OF, title.getId());
        abortParams.setMargins((int) scale * 15, (int) scale * 15, (int) scale * 15, (int) scale * 15);
        abort.setLayoutParams(abortParams);

        RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) title.getLayoutParams();
        //titleParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        titleParams.addRule(RelativeLayout.RIGHT_OF, abort.getId());
        titleParams.setMargins((int) scale * 15, (int) scale * 15, (int) scale * 15, (int) scale * 15);
        title.setLayoutParams(titleParams);

        RelativeLayout.LayoutParams addParams = (RelativeLayout.LayoutParams) add.getLayoutParams();
        addParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addParams.addRule(RelativeLayout.LEFT_OF, title.getId());
        addParams.setMargins((int) scale * 15, (int) scale * 15, (int) scale * 15, (int) scale * 15);
        add.setLayoutParams(addParams);

        RelativeLayout.LayoutParams doneParams = (RelativeLayout.LayoutParams) done.getLayoutParams();
        doneParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        doneParams.setMargins((int) scale * 15, (int) scale * 15, (int) scale * 15, (int) scale * 15);
        done.setLayoutParams(doneParams);

        switch (whichOne) {
            case Variables.ADD:
                title.setText(R.string.messages_new_head);
                title.setVisibility(View.VISIBLE);
                abort.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                break;
            case Variables.SHOW:
                title.setText(R.string.menu_messages);
                title.setVisibility(View.VISIBLE);
                abort.setVisibility(View.GONE);
                done.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                break;
            default:
                Snackbar.make(f.requireView(), R.string.error_site_messages, Snackbar.LENGTH_SHORT).show();
                break;
        }

        return titleTL;
    }
}
