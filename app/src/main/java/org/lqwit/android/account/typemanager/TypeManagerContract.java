package org.lqwit.android.account.typemanager;

import org.lqwit.android.account.BasePresenter;
import org.lqwit.android.account.BaseView;
import org.lqwit.android.account.entity.Type;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/2.
 * This specifies the contract between the view and the presenter.
 */
public interface TypeManagerContract  {

    interface View extends BaseView<Presenter>{

        void showTypeList(List<Type> types);

        void showAddType(int typeFlag, String typeId);

        boolean isActive();
    }


    interface Presenter extends BasePresenter{

        /**
         *
         * @param typeFlag 0: income 1: expend
         * @param typeId if typeId == null -> add  else -> update
         */
        void addNewType(int typeFlag, String typeId);

    }
}
