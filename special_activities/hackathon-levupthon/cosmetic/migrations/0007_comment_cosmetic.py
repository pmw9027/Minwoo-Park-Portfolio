# Generated by Django 2.2.4 on 2019-11-30 12:15

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('cosmetic', '0006_auto_20191130_1124'),
    ]

    operations = [
        migrations.AddField(
            model_name='comment',
            name='cosmetic',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='cosmetic.Cosmetic'),
        ),
    ]
