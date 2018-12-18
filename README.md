# LazyFragmentDemo
fragment懒加载

在使用Fragment时候，我们常常要不配合ViewPager来使用，要不就配合FragmentManager来使用。但是由于ViewPager的性质，导致用户往往在还没点击需要展示的Fragment
的时候就去网络断加载数据，导致的结果就是白白浪费了流量，且消耗了app的性能。这个时候我们就需要对fragment进行改造，让他在用户点击显示的时候去加载数据。
保证性能。

fragment中有一个方法，叫做setUserVisibleHint(boolean isVisibleToUser)。顾名思义，对用户可见的时候才会去调用这个方法。也就是参数
isVisibleToUser = true 的时候。这个方法在onActivityCreat()之前调用。但是为了保证对用户进行显示所有控件数据才得以加载数据，所以这里我们需要设置几个
参数来进行操作。

    public boolean isUserVisibilty ; //是否对用户可见
    public boolean isCreatView ; //控件是否加载完成。由于可能出现一种情况，就是在onCreatView中控件还未加载完成，便将数据set到控件中，会造成程序出现问题。
    public boolean isloadData ; //数据是否加载过。不需要每次加载数据，第一次加载完成之后，就不用再次进行加载。
    
    
```
public abstract class BaseViewPagerFragment extends Fragment {

    private Unbinder bind;

    public BaseViewPagerFragment(){}
    public boolean isUserVisibilty = false;
    public boolean isCreatView = false;
    public boolean isloadData = false;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    /**
     * 初始化基础数据，例如对变量进行赋值操作等。
     */
    abstract public void initData();

    /**
     * 设置布局样式
     * @return
     */
    abstract public int initLayout();


    /**
     * 初始化控件
     */
    abstract public void initView();

    /**
     * 网络数据的请求与加载
     */
    abstract public void loadData();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(),container,false);
        bind = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isUserVisibilty = isVisibleToUser;
        tryToLoadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isCreatView = true;
        tryToLoadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public void tryToLoadData(){
        if (isUserVisibilty && isCreatView && !isloadData){
            loadData();
            isloadData = true;
        }
    }
}
```

由于onActivityCreated()的生命周期在onCreatView()之后调用，所以我们在其里面将isCreatView = true能够确保控件都加载
完成之后再进行操作。
