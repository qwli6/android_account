package org.lqwit.android.type.manager;

import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;
import org.lqwit.android.data.entity.Type;

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
