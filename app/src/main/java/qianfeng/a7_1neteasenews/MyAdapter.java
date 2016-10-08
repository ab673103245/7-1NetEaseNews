package qianfeng.a7_1neteasenews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class MyAdapter extends BaseAdapter {
    private List<NewsBean> list;
    private Context context;
    private LayoutInflater inflater;

    private static final int VIEWPAGER_TYPE = 0;
    private static final int PHOTOSET_TYPE = 1;
    private static final int GEN_TYPE = 2;

    public MyAdapter(List<NewsBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {

        int type = list.get(position).getType();
        return type;
//        if(type == VIEWPAGER_TYPE)
//        {
//            return VIEWPAGER_TYPE;
//        }else if(type == PHOTOSET_TYPE)
//        {
//            return PHOTOSET_TYPE;
//        }else
//        {
//            return GEN_TYPE;
//        }
//        // 这里要重写比较的规则，就是外面记录的tpye和里面ListView的type，这个type决定ListView该加载哪个布局。
    }

    @Override
    public int getViewTypeCount() {
        return 3; // 有多少种类型，这里就写多少种类型，是一个固定的数字，缓冲池里面预加载的item数就是这里决定的。
        // 预加载是一个，就是只有一种item。这里三种item，所以预加载就是3个。
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VpHolder vpHolder = null;
        PsHolder psHolder = null;
        GenHolder genHolder = null;

        int itemViewType = getItemViewType(position);



        if(convertView == null)
        {
            switch (itemViewType)
            {
                case VIEWPAGER_TYPE:
                    convertView = inflater.inflate(R.layout.viewpager_item,parent,false);
                    vpHolder = new VpHolder();
                    vpHolder.vp = (ViewPager) convertView.findViewById(R.id.viewpager);
                    convertView.setTag(vpHolder);
                    break;

                case PHOTOSET_TYPE:
                    convertView = inflater.inflate(R.layout.imgextra_item,parent,false);
                    psHolder = new PsHolder();
                    psHolder.title = (TextView) convertView.findViewById(R.id.title);
                    psHolder.iv1 = (ImageView) convertView.findViewById(R.id.iv1);
                    psHolder.iv2 = (ImageView) convertView.findViewById(R.id.iv2);
                    psHolder.iv3 = (ImageView) convertView.findViewById(R.id.iv3);
                    convertView.setTag(psHolder);
                    break;

                case GEN_TYPE:
                    convertView = inflater.inflate(R.layout.gen_item,parent,false);
                    genHolder = new GenHolder();
                    genHolder.title = (TextView) convertView.findViewById(R.id.title);
                    genHolder.iv1 = (ImageView) convertView.findViewById(R.id.iv1);
                    convertView.setTag(genHolder);
                    break;
            }
        }else
        {
            switch (itemViewType)
            {
                case VIEWPAGER_TYPE:

                    vpHolder = (VpHolder) convertView.getTag();
                    break;

                case PHOTOSET_TYPE:
                    psHolder = (PsHolder) convertView.getTag();
                    break;

                case GEN_TYPE:
                   genHolder = (GenHolder) convertView.getTag();
                    break;
            }
        }

        NewsBean newsBean = list.get(position);

        switch (itemViewType)
        {
            case VIEWPAGER_TYPE:
//                vpHolder.vp.setAdapter();
                String[] imgextra = newsBean.getImgextra();
                VpAdapter vpAdapter = new VpAdapter(context, imgextra);
                vpHolder.vp.setAdapter(vpAdapter);
                break;

            case PHOTOSET_TYPE:
                psHolder.title.setText(newsBean.getTitle());
                String[] imgextra1 = newsBean.getImgextra();
                Picasso.with(context).load(newsBean.getImgsrc()).into(psHolder.iv1);
                Picasso.with(context).load(imgextra1[0]).into(psHolder.iv2);
                Picasso.with(context).load(imgextra1[1]).into(psHolder.iv3);
                break;

            case GEN_TYPE:
                genHolder.title.setText(newsBean.getTitle());
                Picasso.with(context).load(newsBean.getImgsrc()).into(genHolder.iv1);
                break;
        }

        return convertView;
    }


    class VpHolder {
        ViewPager vp;
    }

    class PsHolder {
        TextView title;
        ImageView iv1,iv2,iv3;
    }

    class GenHolder
    {
        TextView title;
        ImageView iv1;
    }


}
