package org.ovirt.engine.ui.webadmin.section.main.presenter.tab.cluster;

import org.ovirt.engine.core.common.businessentities.VDSGroup;
import org.ovirt.engine.core.common.businessentities.network.Network;
import org.ovirt.engine.ui.common.place.PlaceRequestFactory;
import org.ovirt.engine.ui.common.presenter.AbstractSubTabPresenter;
import org.ovirt.engine.ui.common.uicommon.model.SearchableDetailModelProvider;
import org.ovirt.engine.ui.common.widget.tab.ModelBoundTabData;
import org.ovirt.engine.ui.uicommonweb.models.clusters.ClusterListModel;
import org.ovirt.engine.ui.uicommonweb.models.clusters.ClusterNetworkListModel;
import org.ovirt.engine.ui.webadmin.ApplicationConstants;
import org.ovirt.engine.ui.webadmin.place.ApplicationPlaces;
import org.ovirt.engine.ui.webadmin.section.main.presenter.tab.ClusterSelectionChangeEvent;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;

public class SubTabClusterNetworkPresenter extends AbstractSubTabPresenter<VDSGroup, ClusterListModel, ClusterNetworkListModel, SubTabClusterNetworkPresenter.ViewDef, SubTabClusterNetworkPresenter.ProxyDef> {

    @ProxyCodeSplit
    @NameToken(ApplicationPlaces.clusterNetworkSubTabPlace)
    public interface ProxyDef extends TabContentProxyPlace<SubTabClusterNetworkPresenter> {
    }

    public interface ViewDef extends AbstractSubTabPresenter.ViewDef<VDSGroup> {
    }

    @TabInfo(container = ClusterSubTabPanelPresenter.class)
    static TabData getTabData(ApplicationConstants applicationConstants,
            SearchableDetailModelProvider<Network, ClusterListModel, ClusterNetworkListModel> modelProvider) {
        return new ModelBoundTabData(applicationConstants.clusterNetworkSubTabLabel(), 1, modelProvider);
    }

    @Inject
    public SubTabClusterNetworkPresenter(EventBus eventBus, ViewDef view, ProxyDef proxy,
            PlaceManager placeManager,
            SearchableDetailModelProvider<Network, ClusterListModel, ClusterNetworkListModel> modelProvider) {
        super(eventBus, view, proxy, placeManager, modelProvider,
                ClusterSubTabPanelPresenter.TYPE_SetTabContent);
    }

    @Override
    protected PlaceRequest getMainTabRequest() {
        return PlaceRequestFactory.get(ApplicationPlaces.clusterMainTabPlace);
    }

    @ProxyEvent
    public void onClusterSelectionChange(ClusterSelectionChangeEvent event) {
        updateMainTabSelection(event.getSelectedItems());
    }

}
