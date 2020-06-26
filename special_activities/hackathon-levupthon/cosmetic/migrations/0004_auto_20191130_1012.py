# Generated by Django 2.2.4 on 2019-11-30 10:12

from django.db import migrations, models
import django.utils.timezone


class Migration(migrations.Migration):

    dependencies = [
        ('cosmetic', '0003_auto_20191130_0951'),
    ]

    operations = [
        migrations.AddField(
            model_name='cosmetic',
            name='created_at',
            field=models.DateTimeField(auto_now_add=True, default=django.utils.timezone.now),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='cosmetic',
            name='updated_at',
            field=models.DateTimeField(auto_now=True),
        ),
    ]
