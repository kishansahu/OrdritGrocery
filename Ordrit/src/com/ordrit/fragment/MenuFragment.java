package com.ordrit.fragment;

import java.util.HashSet;
import java.util.Set;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ordrit.R;
import com.ordrit.adapter.TreeListAdapter;
import com.ordrit.treeview.InMemoryTreeStateManager;
import com.ordrit.treeview.TreeBuilder;
import com.ordrit.treeview.TreeStateManager;
import com.ordrit.treeview.TreeViewList;
import com.ordrit.util.FragmentConstant;

public class MenuFragment extends BaseFragment {
	
	private View menuFragment;
	private Button menuAccount, menuBag, menuOrderStatus,
			menuPreviousOrder, menuCoupon, menuHelp, menuShoping;


    private final Set<Long> selected = new HashSet<Long>();

    private TreeViewList treeView;

    private static final int[] DEMO_NODES = new int[] { 0, 0,0, 1, 1, 2, 2, 1,
            1, 2, 1, 0, 0, 0/*, 1, 2, 3, 2, 0, 0, 1, 2, 0, 1, 2, 0, 1*/ };
    private static final int LEVEL_NUMBER = 4;
    private TreeStateManager<Long> manager = null;
    private TreeListAdapter simpleStandardAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		menuFragment=inflater.inflate(R.layout.fragment_menu, container,false);
		setupUiComponent();
		return menuFragment;
	}
	@Override
	void setupUiComponent() {
		menuAccount=(Button)menuFragment.findViewById(R.id.menuAccount);
		menuAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
				ManageUserInfoFragment manageUserInfoFragment = new ManageUserInfoFragment();
				dashboardActivity.commitFragment(manageUserInfoFragment,FragmentConstant.MENU_BAG_FRAGMENT);
				
			
				
			}
		});
		menuBag=(Button)menuFragment.findViewById(R.id.menuBag);
		menuBag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
				MenuBagFragment menuBagFragment = new MenuBagFragment();
				dashboardActivity.commitFragment(menuBagFragment,FragmentConstant.MENU_BAG_FRAGMENT);
				
				
			}
		});
		menuOrderStatus=(Button)menuFragment.findViewById(R.id.menuOrderStatus);
		menuOrderStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuPreviousOrder=(Button)menuFragment.findViewById(R.id.menuPreviousOrder);
		menuPreviousOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuCoupon=(Button)menuFragment.findViewById(R.id.menuCoupon);
		menuCoupon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuHelp=(Button)menuFragment.findViewById(R.id.menuHelp);
		menuHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		menuShoping=(Button)menuFragment.findViewById(R.id.menuShoping);
		menuShoping.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
				CatogeryFragment catogeryFragment = new CatogeryFragment();
				dashboardActivity.commitFragment(catogeryFragment,FragmentConstant.CATEGORY_FRAGMENT);
				
				
			}
		});
		 manager = new InMemoryTreeStateManager<Long>();
         final TreeBuilder<Long> treeBuilder = new TreeBuilder<Long>(manager);
         for (int i = 0; i < DEMO_NODES.length; i++) {
             treeBuilder.sequentiallyAddNextNode((long) i, DEMO_NODES[i]);
         }
         treeView = (TreeViewList) menuFragment.findViewById(R.id.mainTreeView);
       
         simpleStandardAdapter = new TreeListAdapter(dashboardActivity, selected, manager,
                 LEVEL_NUMBER);
         treeView.setAdapter(simpleStandardAdapter);
         treeView.setCollapsible(true);
         manager.collapseChildren(null);
       //  treeView.setCollapsible(false);
	}

}
