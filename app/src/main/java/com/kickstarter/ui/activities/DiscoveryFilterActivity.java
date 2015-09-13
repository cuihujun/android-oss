package com.kickstarter.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.kickstarter.R;
import com.kickstarter.libs.ApiCapabilities;
import com.kickstarter.libs.BaseActivity;
import com.kickstarter.libs.KSColorUtils;
import com.kickstarter.libs.RequiresPresenter;
import com.kickstarter.presenters.DiscoveryFilterPresenter;
import com.kickstarter.ui.adapters.DiscoveryFilterAdapter;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresPresenter(DiscoveryFilterPresenter.class)
public class DiscoveryFilterActivity extends BaseActivity<DiscoveryFilterPresenter> {
  DiscoveryFilterAdapter adapter;
  LinearLayoutManager layoutManager;

  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @BindColor(R.color.dark_blue_gradient_start) int darkBlueGradientStartColor;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.discovery_filter_layout);
    ButterKnife.bind(this);

    layoutManager = new LinearLayoutManager(this);
    adapter = new DiscoveryFilterAdapter();
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    setStatusBarColor();
  }

  @OnClick(R.id.close_button)
  public void closeActivity() {
    onBackPressed();
  }

  private void setStatusBarColor() {
    if (ApiCapabilities.canSetStatusBarColor()) {
      final Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(darkBlueGradientStartColor);
    }
  }
}
