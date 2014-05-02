may-springsportscenter-amazon-mysql-memcached
=============================================
remarks:
  for the models, we have two groups:
    1)admin, hall, court,capability  ---> if we delete a hall, all the admin, court, capability related with this hall will be all deleted(also the join table for many-to-many), but we can't delete an admin/court/capability separately(already in a relation), but can delete it if it's a created one(no relation yet)
    2)timeInterval, reservation, subscriber
    same here, if we delete the subscriber, the timeInterval/reservation will be deleted automatically(including the join table for many-to-many)
    
    for updating, it's working except for the TimeInterval updating, check TimeIntervalController.class, line 139th, you know how to get the Date right(because here i can't get the date), if you can get the date there when you modifing, then it will work, other model updating are working.
