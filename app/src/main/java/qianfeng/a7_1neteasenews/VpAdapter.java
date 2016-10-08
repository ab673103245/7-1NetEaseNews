package qianfeng.a7_1neteasenews;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class VpAdapter extends PagerAdapter {

    private Context context;
    private String[] imgextra;

    public VpAdapter(Context context, String[] imgextra) {
        this.context = context;
        this.imgextra = imgextra;
    }

    @Override
    public int getCount() {
        return imgextra.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView iv = new ImageView(context);
        Picasso.with(context).load(imgextra[position]).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
