package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.FragmentQuanAo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.Photo;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.KH_Loader.KH_QuanAo_Loader;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.R;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Support.SliderAdapter;

public class aogioFragment extends Fragment {

    List<Photo> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aogio, container, false);
        SliderView sliderView = view.findViewById(R.id.sliderView);

        list = setListPhoto();
        SliderAdapter sliderAdapter = new SliderAdapter(list);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        KH_QuanAo_Loader kh_quanAo_loader = new KH_QuanAo_Loader(getContext(), view.findViewById(R.id.recyclerView_Laptop_HP));
        kh_quanAo_loader.execute("LAoThun");

        return view;
    }

    private List<Photo> setListPhoto() {
        list.add(new Photo(R.drawable.thun_mot));
        list.add(new Photo(R.drawable.thun_hai));
        list.add(new Photo(R.drawable.thun_ba));
        list.add(new Photo(R.drawable.thun_bon));
        list.add(new Photo(R.drawable.thun_nam));
        return list;
    }
}