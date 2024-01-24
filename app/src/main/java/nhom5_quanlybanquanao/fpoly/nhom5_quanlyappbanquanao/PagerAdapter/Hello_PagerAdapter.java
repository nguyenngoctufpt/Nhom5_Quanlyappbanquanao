package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.HelloWorld.Hello_Fragment1;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.HelloWorld.Hello_Fragment2;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.HelloWorld.Hello_Fragment3;

public class Hello_PagerAdapter extends FragmentStatePagerAdapter {
    public Hello_PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Hello_Fragment1();
            case 1:
                return new Hello_Fragment2();
            case 2:
                return new Hello_Fragment3();
            default:
                return new Hello_Fragment1();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
