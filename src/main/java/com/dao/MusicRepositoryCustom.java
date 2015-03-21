package com.dao;

import com.bean.Music;

/**
 * Created by melkir on 20/03/15.
 */
public interface MusicRepositoryCustom {

    Music update(Integer id, Music newMusic);

}
