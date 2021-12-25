package com.bankass.bankass.utils;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

@SuppressWarnings("rawtypes")
public interface ITableDataCreator<D extends RecursiveTreeObject, T> {

	D onCreate(T data);
}
