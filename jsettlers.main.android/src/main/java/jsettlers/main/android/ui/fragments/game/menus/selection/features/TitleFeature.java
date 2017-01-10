package jsettlers.main.android.ui.fragments.game.menus.selection.features;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jsettlers.common.buildings.IBuilding;
import jsettlers.graphics.androidui.utils.OriginalImageProvider;
import jsettlers.graphics.localization.Labels;
import jsettlers.graphics.map.controls.original.panel.selection.BuildingState;
import jsettlers.main.android.R;
import jsettlers.main.android.controls.ControlsAdapter;

/**
 * Created by tompr on 10/01/2017.
 */

public class TitleFeature extends SelectionFeature {

    public TitleFeature(IBuilding building, ControlsAdapter controls, View view) {
        super(building, controls, view);
    }

    @Override
    public void initialize(BuildingState buildingState, ControlsAdapter controls) {
        super.initialize(buildingState, controls);

        TextView nameTextView = (TextView) getView().findViewById(R.id.text_view_name);
        ImageView imageView = (ImageView) getView().findViewById(R.id.image_view);

        String name = Labels.getName(getBuilding().getBuildingType());
        if (getBuildingState().isConstruction()) {
            name = Labels.getString("building-build-in-progress", name);
        }

        nameTextView.setText(name);
        OriginalImageProvider.get(getBuilding().getBuildingType()).setAsImage(imageView);
    }
}
