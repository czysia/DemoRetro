package com.example.mob403demo3retro.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.CatogoryAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentCatorogyCommonBinding;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.viewmodel.CatogoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CatorogyCommonFragment extends Fragment implements CatogoryAdapter.onItemCategoryClick {

    private FragmentCatorogyCommonBinding mBinding;
    CatogoryViewModel mViewModel;
    CatogoryAdapter mAdapter;
    public List<Product> mProductList = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CatorogyCommonFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CatorogyCommonFragment newInstance(String param1, String param2) {
        CatorogyCommonFragment fragment = new CatorogyCommonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentCatorogyCommonBinding.inflate(inflater, container, false);
        mBinding.tvTitle.setText(mParam1);
        onClick();
        setUpViewModel();
        setUpAdapter();
        return mBinding.getRoot();
    }

    public void onClick() {
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });
    }

    public void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CatogoryViewModel.class);
        mViewModel.getListData(mParam2);
        mViewModel.listData.observe(getViewLifecycleOwner(), data -> {
            mProductList.clear();
            mProductList.addAll(data);
            mBinding.spinKit.setVisibility(View.GONE);
            mBinding.recycleProduct.getAdapter().notifyDataSetChanged();
        });
    }

    public void setUpAdapter() {
        mAdapter = new CatogoryAdapter(mProductList, getContext(), this);
        mBinding.recycleProduct.setAdapter(mAdapter);
    }

    @Override
    public void ItemClick(Product items) {
        ArrayList arrayList = new ArrayList<String>();
        arrayList.addAll(items.getImage());
        gotaDetail(items.getId(), items.getName(), items.getPrice(), items.getPackaging(), arrayList);
    }

    public void gotaDetail(String idProduce, String name, int prices, String des, ArrayList<String> listImage) {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new DetailProductFragment().newInstance(idProduce, name, prices, des, listImage), "preview").commit();
    }
}