"""LEVUPTHON_Team18 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from cosmetic import views as cosmetic_views
from django.conf.urls.static import static
from django.conf import settings

urlpatterns = [
    path('', cosmetic_views.home, name='home'),
    path('admin/', admin.site.urls),        
    path('cosmetics/<int:cosmetic_id>', cosmetic_views.CosmeticAPIView.as_view(), name='cosmetic_detail'),
    path('cosmetics/', cosmetic_views.cosmetic_list, name='cosmetics'),    
    path('comments', cosmetic_views.CommentAPIView.as_view()),
    path('accounts/', include('account.urls')), 

]

urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)