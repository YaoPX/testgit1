# Sample data for COMP1721 Coursework 1

**Do not alter or remove any of the files in this directory.**

The file `leeds.csv` contains hourly measurements from a weather station
at Knowsthorpe Gate in Leeds, spanning the period 23 March 2011 to
31 December 2017.

The columns are:

* Date and time
* Wind direction (degrees)
* Wind speed (metres per second)
* Standard deviation in wind direction (degrees)
* Temperature measured at 2 metres
* Temperature measured at 8 metres
* Solar irradiance (Watts per square metre)
* Relative humidity (% of moisture in air)

Note: this is a real dataset, and real datasets are messy! There are
lengthy 'blank' periods where no measurements were made, due to the
equipment being faulty or undergoing repairs.  Also, individual records
will sometimes not contain a complete set of measurements.

Note that you will only need to use date and time, wind speed, temperature
at 2 metres, solar irradiance and relative humidity in this assignment.
The other fields should be ignored.

See [Data Mill North][1] for further details.

The other CSV files in this directory are used for testing your code.
You can safely ignore these (but make sure you don't rename, alter or
remove any of them).

[1]: https://datamillnorth.orgdd/dataset/leeds-meteorological-data
