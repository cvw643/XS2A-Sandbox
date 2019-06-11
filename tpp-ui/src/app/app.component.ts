import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
    title = 'app';
    subscription = new Subscription();

    constructor(private authService: AuthService) {
    }

    ngOnInit() {
        // subscribe every minute and check if token is still valid
        this.subscription = this.authService.timerSubject.subscribe(time => {
            //  console.log(time)
            if (!this.authService.isLoggedIn()) {
                this.authService.logout();
            }
        })
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
