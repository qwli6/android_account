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

        boolean isActive();
    }


    interface Presenter extends BasePresenter{

        void saveType(String name, String picName, Integer type);

    }
}
