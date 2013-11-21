from django.conf.urls.defaults import *
from django.contrib import admin
import settings

# Uncomment the next two lines to enable the admin:
#from django.contrib import admin
#admin.autodiscover()

urlpatterns = patterns('',
    # Example:
    # (r'^serverweb/', include('serverweb.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    #(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # (r'^admin/', include(admin.site.urls)),
    
    # Required to make static serving work 
    (r'^libs/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.MEDIA_ROOT}),
    
    # ajax
    (r'^ajax/sensors/latest$', 'headquarter.views.latest_sensors'),
    (r'^ajax/notifies/latest/(?P<limit>\d+)$', 'headquarter.views.get_notifies'),
    
    (r'^$', 'headquarter.views.index'),
)
