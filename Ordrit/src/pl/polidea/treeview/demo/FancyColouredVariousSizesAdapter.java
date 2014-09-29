package pl.polidea.treeview.demo;

import java.util.Set;

import pl.polidea.treeview.TreeNodeInfo;
import pl.polidea.treeview.TreeStateManager;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ordrit.R;

public final class FancyColouredVariousSizesAdapter extends SimpleStandardAdapter {
    public FancyColouredVariousSizesAdapter(final Activity activity,
            final Set<Long> selected,
            final TreeStateManager<Long> treeStateManager,
            final int numberOfLevels) {
        super(activity, selected, treeStateManager, numberOfLevels);
    }

    @Override
    public LinearLayout updateView(final View view,
            final TreeNodeInfo<Long> treeNodeInfo) {
        final LinearLayout viewLayout = super.updateView(view, treeNodeInfo);
        final TextView descriptionView = (TextView) viewLayout
                .findViewById(R.id.demo_list_item_description);
       /* final TextView levelView = (TextView) viewLayout
                .findViewById(R.id.demo_list_item_level);*/
        descriptionView.setTextSize(22 - 3 * treeNodeInfo.getLevel());
      //  levelView.setTextSize(20 - 2 * treeNodeInfo.getLevel());
        return viewLayout;
    }

    @Override
    public Drawable getBackgroundDrawable(final TreeNodeInfo<Long> treeNodeInfo) {
        switch (treeNodeInfo.getLevel()) {
        case 0:
            return new ColorDrawable(Color.parseColor("#FFFFFF"));
        case 1:
            return new ColorDrawable(Color.parseColor("#F2F1F0"));
        case 2:
            return new ColorDrawable(Color.parseColor("#F7F6F4"));
        default:
            return null;
        }
    }
}