package com.emudhra.emra.subscriber.dto;

import java.util.List;

import com.emudhra.emra.subscriber.entity.master.UserMaster;



public class DataTableResponse<T> {
    private List<T> data;
    private long recordsTotal;
    private long recordsFiltered;

    public DataTableResponse(List<T> data, long recordsTotal, long recordsFiltered) {
        this.data = data;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

	public void setData(List<UserMaster> employeeList) {
		// TODO Auto-generated method stub
		
	}

	public void setRecordsTotal(long totalRecords) {
		// TODO Auto-generated method stub
		
	}

	public void setRecordsFiltered(long totalRecords) {
		// TODO Auto-generated method stub
		
	}
}

