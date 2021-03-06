var myApp = angular.module('PubNubChat',[]).directive('coolFade', function() {
    return {
      compile: function(elm) {
        //console.log('compiling');
        $(elm).css('opacity', 0);
        return function(scope, elm, attrs) {
         // console.log('animating');
          $(elm).animate({ opacity : 1.0 }, 1000 );
        };
      }
    };
  });


//myApp.directive('myDirective', function() {});
//myApp.factory('myService', function() {});

function MyCtrl($scope) {
    
    $scope.messages = [];
    $scope.realtimeStatus = "Connecting...";
    $scope.channel = "pubnub_chat";
    $scope.limit = 20;

    //publish a chat message
    $scope.publish = function(){
        
        //toggle the progress bar
        $('#progress_bar').slideToggle();
        
         PUBNUB.publish({
                channel : $scope.channel,
                message : $scope.message
            }) 
             
        //reset the message text
       $scope.message.text = "";
    }
        
    //gets the messages history   
    $scope.history = function(){
        PUBNUB.history( {
            channel : $scope.channel,
            limit   : $scope.limit
        }, function(messages) {
            // Shows All Messages
            $scope.$apply(function(){
                $scope.messages = messages.reverse();          
                
            }); 
        } );
     }
         

   //we'll leave these ones as is so that pubnub can
   //automagically trigger the events
   PUBNUB.subscribe({
        channel    : $scope.channel,
        restore    : false, 
    
        callback   : function(message) { 
            
            //toggle the progress_bar
            $('#progress_bar').slideToggle();         
         
            $scope.$apply(function(){
                $scope.messages.unshift(message);          
                
            }); 
        },
    
        disconnect : function() {   
            $scope.$apply(function(){
                $scope.realtimeStatus = 'Disconnected';
            });
        },
    
        reconnect  : function() {   
            $scope.$apply(function(){
                $scope.realtimeStatus = 'Connected';
            });
        },
    
        connect    : function() {  
            $scope.$apply(function(){
                $scope.realtimeStatus = 'Connected';
                //hide the progress bar
                $('#progress_bar').slideToggle();
                //load the message history from PubNub
                $scope.history();
            });
    
        }
    })
 
    
}