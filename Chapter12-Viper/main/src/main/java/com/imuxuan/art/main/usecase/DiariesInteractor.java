package com.imuxuan.art.main.usecase;

import com.imuxuan.art.main.DiariesContract;

public class DiariesInteractor implements DiariesContract.Interactor {

    private GetAllDiariesUseCase getAllDiariesUseCase;

    @Override
    public GetAllDiariesUseCase getAll() {
        if (getAllDiariesUseCase == null) {
            synchronized (this) {
                if (getAllDiariesUseCase == null) {
                    getAllDiariesUseCase = new GetAllDiariesUseCase();
                }
            }
        }
        return getAllDiariesUseCase;
    }
}
